import { useState } from "react";
import { travellingApi } from "../services/travellingApi";

export default function CreateTravelForm({ onCreated }) {
    const [form, setForm] = useState({
        destination: "",
        date: "",
        description: "",
        rating: ""
    });
    const [submitting, setSubmitting] = useState(false);

    const change = (e) => setForm({ ...form, [e.target.name]: e.target.value });

    const submit = async (e) => {
        e.preventDefault();
        setSubmitting(true);
        try {
            const payload = {
                destination: form.destination,
                date: form.date,
                description: form.description || null,
                rating: form.rating ? Number(form.rating) : null
            };
            await travellingApi.create(payload);
            setForm({ destination: "", date: "", description: "", rating: "" });
            onCreated?.();
        } catch (error) {
            alert("Failed to create travel: " + error.message);
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div style={styles.formContainer}>
            <h2 style={styles.formTitle}>✈️ Add New Trip</h2>
            <div onSubmit={submit}>
                <div style={styles.formGrid}>
                    <div>
                        <label style={styles.label}>Destination *</label>
                        <input
                            name="destination"
                            placeholder="e.g., Paris, Tokyo..."
                            value={form.destination}
                            onChange={change}
                            required
                            style={styles.input}
                        />
                    </div>

                    <div>
                        <label style={styles.label}>Date *</label>
                        <input
                            type="date"
                            name="date"
                            value={form.date}
                            onChange={change}
                            required
                            style={styles.input}
                        />
                    </div>
                </div>

                <div style={styles.formGroup}>
                    <label style={styles.label}>Rating (0-5)</label>
                    <input
                        name="rating"
                        type="number"
                        step="0.1"
                        min="0"
                        max="5"
                        placeholder="e.g., 4.5"
                        value={form.rating}
                        onChange={change}
                        style={styles.input}
                    />
                </div>

                <div style={styles.formGroup}>
                    <label style={styles.label}>Review</label>
                    <textarea
                        name="description"
                        placeholder="Share your experience..."
                        value={form.description}
                        onChange={change}
                        rows={3}
                        style={styles.textarea}
                    />
                </div>

                <button
                    onClick={submit}
                    disabled={submitting || !form.destination || !form.date}
                    style={{
                        ...styles.submitButton,
                        opacity: (submitting || !form.destination || !form.date) ? 0.6 : 1,
                        cursor: (submitting || !form.destination || !form.date) ? 'not-allowed' : 'pointer'
                    }}
                >
                    {submitting ? '⏳ Adding...' : 'Add Trip'}
                </button>
            </div>
        </div>
    );
}

const styles = {
    formContainer: {
        background: 'white',
        padding: '24px',
        borderRadius: '12px',
        boxShadow: '0 2px 8px rgba(0,0,0,0.08)',
        marginBottom: '32px'
    },
    formTitle: {
        margin: '0 0 20px 0',
        fontSize: '20px',
        fontWeight: '600',
        color: '#1a1a1a'
    },
    formGrid: {
        display: 'grid',
        gridTemplateColumns: '1fr 1fr',
        gap: '16px',
        marginBottom: '16px'
    },
    formGroup: {
        marginBottom: '16px'
    },
    label: {
        display: 'block',
        marginBottom: '6px',
        fontSize: '14px',
        fontWeight: '500',
        color: '#555'
    },
    input: {
        width: '100%',
        padding: '10px 12px',
        border: '1px solid #e0e0e0',
        borderRadius: '8px',
        fontSize: '14px',
        fontFamily: 'inherit',
        outline: 'none',
        transition: 'border-color 0.2s'
    },
    textarea: {
        width: '100%',
        padding: '10px 12px',
        border: '1px solid #e0e0e0',
        borderRadius: '8px',
        fontSize: '14px',
        fontFamily: 'inherit',
        resize: 'vertical',
        outline: 'none',
        transition: 'border-color 0.2s'
    },
    submitButton: {
        width: '100%',
        padding: '12px 24px',
        background: '#4f46e5',
        color: 'white',
        border: 'none',
        borderRadius: '8px',
        fontSize: '15px',
        fontWeight: '600',
        transition: 'background 0.2s'
    }
};
